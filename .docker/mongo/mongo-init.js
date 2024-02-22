db.createUser(
  {
    user: "mongoadmin",
    pwd: "identity123",
    roles: [
      {
        role: "readWrite",
        db: "identity"
      }
    ]
  }
);

db.identity.insertOne({
    number: "112380340",
    expiryDate: new ISODate("2024-09-30T00:00:00Z"),
    emissionDate: new ISODate("2022-05-17T00:00:00Z"),
    documentType: {
        code: "CI",
        name: "Cédula de identidad."
    }
});