module demo.domain {
    exports pl.beautify.schedule.domain to demo.application;
    exports pl.beautify.schedule.domain.state to demo.application, demo.infrastructure, infra.quarkus;
}
