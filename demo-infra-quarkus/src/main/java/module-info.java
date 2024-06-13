module infra.quarkus {
    requires jakarta.cdi;
    requires jakarta.ws.rs;
    requires jakarta.persistence;
    requires quarkus.hibernate.orm.panache;
    requires quarkus.narayana.jta;
    requires demo.application;
    requires demo.domain;
}
