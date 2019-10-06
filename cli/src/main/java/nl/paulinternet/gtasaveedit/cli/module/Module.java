package nl.paulinternet.gtasaveedit.cli.module;

public interface Module {
    String getName();

    String getDescription();

    void execute(String[] args) throws Exception;
}