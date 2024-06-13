package pl.beautify.schedule.application.port.out;

public interface TransactionPort {
    void begin();
    void commit();
    void rollback();
}
