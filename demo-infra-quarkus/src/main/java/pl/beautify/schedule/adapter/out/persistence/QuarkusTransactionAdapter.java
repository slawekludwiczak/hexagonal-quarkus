package pl.beautify.schedule.adapter.out.persistence;

import io.quarkus.narayana.jta.QuarkusTransaction;
import jakarta.enterprise.context.ApplicationScoped;
import pl.beautify.schedule.application.port.out.TransactionPort;

@ApplicationScoped
class QuarkusTransactionAdapter implements TransactionPort {
    @Override
    public void begin() {
        QuarkusTransaction.begin();
    }

    @Override
    public void commit() {
        QuarkusTransaction.commit();
    }

    @Override
    public void rollback() {
        QuarkusTransaction.rollback();
    }
}
