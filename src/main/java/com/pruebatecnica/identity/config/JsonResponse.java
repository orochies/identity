package com.pruebatecnica.identity.config;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonResponse {

	private HttpStatus status;
	private AppStatusCode code;
	private String message;
	private Object data;

	private JsonResponse(HttpStatus status, AppStatusCode code, String message, Object data) {

		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status.value();
	}

	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	public AppStatusCode getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public static class Builder {

		private HttpStatus status = HttpStatus.OK;
		private AppStatusCode code = AppStatusCode.SUCCESS;
		private String message = "Solicitud Exitosa!";
		private Object data = null;

		public Builder() {
		}

		public Builder(HttpStatus status) {
			this.status = status;
		}

		public Builder withCode(AppStatusCode errorCode) {
			this.code = errorCode;
			return this;
		}

		public Builder withMessage(String message) {

			this.message = message;
			return this;
		}

		public Builder withData(Object data) {

			this.data = data;
			return this;
		}

		public JsonResponse build() {
			return new JsonResponse(this.status, this.code, this.message, this.data);
		}
	}

}
