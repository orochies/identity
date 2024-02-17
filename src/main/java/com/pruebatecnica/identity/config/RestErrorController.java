package com.pruebatecnica.identity.config;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Locale;

//import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// import jakarta.persistence.EntityNotFoundException;
// import jakarta.persistence.OptimisticLockException;

@ControllerAdvice
public class RestErrorController {

	private static final Logger log = LoggerFactory.getLogger(RestErrorController.class);

	private MessageSource messageSource;

	@Autowired
	public RestErrorController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public JsonResponse processSystemException(HttpMessageNotReadableException ex) {

		String msg = "No se pueden leer los datos enviados, verifique la informacion.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.BAD_REQUEST)
				.withMessage(msg)
				.withCode(AppStatusCode.APP_ERROR)
				.build(); 
		// @formatter:on
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
	public JsonResponse processSystemException(Exception ex) {

		String msg = "Faltan datos requeridos en su solicitud. Verifique su consulta he intente nuevamente.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.BAD_REQUEST)
				.withMessage(msg)
				.withCode(AppStatusCode.SERVER_ERROR)
				.build(); 
		// @formatter:on
	}

	/*@ResponseBody
	@ExceptionHandler({ PersistenceException.class, HibernateException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public JsonResponse processConstraintError(Exception ex) {

		String msg = "Error inesperado de acceso a datos, intente de nuevo.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
				.withMessage(msg)
				.withCode(AppStatusCode.DATABASE_ERROR)
				.build();
		// @formatter:on
	}*/

	@ResponseBody
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public JsonResponse processConstraintError(DataIntegrityViolationException ex) {

		String msg = "Error de integridad de datos, las referencias no existen o deben ser unicas.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.BAD_REQUEST)
				.withMessage(msg)
				.withCode(AppStatusCode.DATABASE_ERROR)
				.build();
		// @formatter:on
	}

	@ResponseBody
	@ExceptionHandler(AppException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public JsonResponse processBusinessException(AppException ex) {

		String msg = ex.getMessage() == null ? "Solicitud invalida." : ex.getMessage();
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.BAD_REQUEST)
				.withMessage(msg)
				.withCode(AppStatusCode.APP_ERROR)
				.build();
		// @formatter:on
	}

	@ResponseBody
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public JsonResponse access(AccessDeniedException ex) {

		String msg = "Acceso no autorizado.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.FORBIDDEN)
				.withMessage(msg)
				.withCode(AppStatusCode.APP_ERROR)
				.build();
		// @formatter:on
	}

	/*@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public JsonResponse processEntityNotFound(EntityNotFoundException ex) {

		String msg = "No se encontro el recurso solicitado.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.NOT_FOUND)
				.withMessage(msg)
				.withCode(AppStatusCode.DATABASE_ERROR)
				.build();
		// @formatter:on
	}*/

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public JsonResponse processValidationError(MethodArgumentNotValidException ex) {

		String msg = "Validacion error.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		var data = processFieldErrors(fieldErrors);
		result.getGlobalErrors().forEach(error -> data.addGlobalError(error.getDefaultMessage()));

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.BAD_REQUEST)
				.withMessage(msg)
				.withCode(AppStatusCode.VALIDATION_ERROR)
				.withData(data)
				.build();
		// @formatter:on
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ HttpMessageNotWritableException.class, ConversionNotSupportedException.class,
			HttpRequestMethodNotSupportedException.class, NullPointerException.class })
	public JsonResponse requestException(Exception ex) {

		String msg = "Solicitud no valida.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
				.withMessage(msg)
				.withCode(AppStatusCode.SERVER_ERROR)
				.build();
		// @formatter:on
	}

	/*@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ OptimisticLockException.class, StaleObjectStateException.class })
	public JsonResponse concurrencyException(Exception ex) {

		String msg = "Hay una nueva version de los datos disponible. Debe actualizar antes de realizar algún cambio.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.BAD_REQUEST)
				.withMessage(msg)
				.withCode(AppStatusCode.CONCURRENCY_ERROR)
				.build();
		// @formatter:on
	}*/

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	public JsonResponse defaultException(Exception ex) {

		String msg = "Error interno.";
		log.error("{} {}", msg, ex.getMessage(), ex);

		// @formatter:off
		return new JsonResponse.Builder(HttpStatus.INTERNAL_SERVER_ERROR)
				.withMessage(msg)
				.withCode(AppStatusCode.SERVER_ERROR)
				.build();
		// @formatter:on
	}

	private ValidationErrorDto processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorDto dto = new ValidationErrorDto();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return dto;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {

		Locale currentLocale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(fieldError, currentLocale);
	}

}
