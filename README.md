## Sample application using Vault to store the connection and credentials to connect to DB2


- Step One: get DB2 running in Docker using [this image](https://hub.docker.com/r/ibmcom/db2express-c/)

  - make sure to run the db2sampl command
  - "2 - Start DB2 and create sample DB"

- Step Two: Install Vault

```bash
brew install vault
vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"
export VAULT_TOKEN="00000000-0000-0000-0000-000000000000"
export VAULT_ADDR="http://127.0.0.1:8200"
vault write secret/db2-sample spring.datasource.url=jdbc:db2://localhost:50000/SAMPLE spring.datasource.username=db2inst1 spring.datasource.password=db2inst1-pwd spring.datasource.hikari.connection-test-query="SELECT 1 FROM sysibm.sysdummy1 WITH UR"
```

- Step Three: Run the application

```bash
./mvnw spring-boot:run
```


#### All of the Datasource configuration is stored in Vault

- Run the command below to see the configuration stored in Vault

```bash
vault read secret/db2-sample
```

- The output should look like this...

```bash
Key                                               Value
---                                               -----
spring.datasource.hikari.connection-test-query    SELECT 1 FROM sysibm.sysdummy1 WITH UR
spring.datasource.password                        db2inst1-pwd
spring.datasource.url                             jdbc:db2://localhost:50000/SAMPLE
spring.datasource.username                        db2inst1
```