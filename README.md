[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Gimme a CLI Starter Project

This project is an example starter project for the [Gimme a CLI library](https://github.com/Nike-Inc/gimme-a-cli).

This project can be forked and used as a starting point for creating your own CLI.

## Features

The starter project includes:

- [Gimme a CLI](https://github.com/Nike-Inc/gimme-a-cli) - a Java library for creating quick and easy CLIs using [JCommander](http://jcommander.org/) and 
[Spring dependency injection](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core).
- An example `Hello World` command.
- An example `--version` command.
- [Shadow Plugin](https://imperceptiblethoughts.com/shadow/) gradle setup to make it easy to distribute your CLI. The
  Shadow plugin creates a fat jar with all dependencies, `sh` and `.bat` wrapper scripts, and `.tar` and `.zip`
  distributions.

## Usage

1. Install the [Java 11 OpenJDK](https://adoptopenjdk.net/).

1. Fork [this project](https://github.com/Nike-Inc/gimme-a-cli-starter-project).

1. Find/replace `mycli` with the name of your CLI.

1. Define one or more commands for your CLI by implementing the Command interface.  Optionally use JCommander
   annotations to define command arguments and options.
    ```java
    import com.beust.jcommander.Parameter;
    import com.beust.jcommander.Parameters;
    import com.nike.gimme.a.cli.Command;
    
    @Parameters(commandNames = "hello",
                commandDescription = "Prints \"Hello <name>\" to the terminal")
    public class HelloWorldCommand implements Command {

        private final Logger log = LoggerFactory.getLogger(getClass());

        @Parameter(names = {"--name"}, required = true)
        private String name;
        
        @Override
        public void execute() {
            log.info("Hello " + name);
        }
    }
    ```
    Commands are automatically instantiated as Spring beans (e.g. dependencies can be supplied via constructor injection, etc).

1. Build and run
    ```bash
    # Create the distribution
    ./gradlew shadowDistTar
 
    # Extract it
    tar xf build/distributions/mycli-shadow-1.0.0-SNAPSHOT.tar
    
    # Run the CLI
    mycli-shadow-1.0.0-SNAPSHOT/bin/mycli --help
    ```
   
1. Optionally define gradle tasks for running CLI commands
   ```groovy
   task helloWorldFromGradle(type: CliExec) {
       description = "Example of defining a CLI command as a Gradle task"
   
       // arguments to pass to the application
       args 'hello', '--name', 'world from gradle'
   }
   ``` 
   This lets you quickly run commands locally without building archives.

## References

- [Gimme a CLI](https://github.com/Nike-Inc/gimme-a-cli) - the library that this project is a "starter project" for.
- [JCommander](http://jcommander.org/) - a library for CLI argument parsing.
- [Spring's IoC](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core) - a library for dependency injection.
- [Shadow Plugin](https://imperceptiblethoughts.com/shadow/) - a gradle plugin for creating a single output jar.
  Makes it easy to distribute your CLI to share with others.
