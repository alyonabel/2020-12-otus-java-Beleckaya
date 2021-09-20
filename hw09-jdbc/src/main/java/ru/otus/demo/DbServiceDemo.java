package ru.otus.demo;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.crm.model.Client;
import ru.otus.crm.service.DbServiceClientImpl;
import ru.otus.crm.datasource.DriverManagerDataSource;
import ru.otus.core.repository.executor.DbExecutorImpl;
import ru.otus.crm.repository.ClientDataTemplateJdbc;
import ru.otus.core.sessionmanager.TransactionManagerJdbc;

import javax.sql.DataSource;

public class DbServiceDemo {
    private static final String URL = "jdbc:postgresql://localhost:5430/demoDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";

    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);//взяли connection
        flywayMigrations(dataSource); //накатили миграцию
        var transactionManager = new TransactionManagerJdbc(dataSource);//собираем наше приложение по частям(Inverse control)
        var dbExecutor = new DbExecutorImpl();
///
        var clientTemplate = new ClientDataTemplateJdbc(dbExecutor); //та штука, которая выполняет select

///
        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate); //все части кладём в сервис, который будет работать с клиентами
        dbServiceClient.saveClient(new Client("dbServiceFirst"));//создаём первого клиента

        var clientSecond = dbServiceClient.saveClient(new Client("dbServiceSecond"));//второго клиента
        var clientSecondSelected = dbServiceClient.getClient(clientSecond.getId()) //селектим клиента, хотим на него посмотреть
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);
///
        dbServiceClient.saveClient(new Client(clientSecondSelected.getId(), "dbServiceSecondUpdated")); //клиента, которого селектили, хотим обновить, другое имя ему даём
        var clientUpdated = dbServiceClient.getClient(clientSecondSelected.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecondSelected.getId()));
        log.info("clientUpdated:{}", clientUpdated);

        log.info("All clients");
        dbServiceClient.findAll().forEach(client -> log.info("client:{}", client)); //хотим селектнуть всю нашу базу
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
