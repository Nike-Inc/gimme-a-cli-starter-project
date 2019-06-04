/*
 * Copyright (c) 2019 Nike, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nike.mycli.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.nike.gimme.a.cli.Command;
import com.nike.gimme.a.cli.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Parameters(commandNames = "hello",
        commandDescription = "Outputs the string \"Hello <name>\"")
public class HelloWorldCommand implements Command {

    @Parameter( names = { "--name" }, description = "Provide the name to say hello to")
    private String name = "World!";

    private final Terminal terminal;

    @Autowired
    public HelloWorldCommand(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void execute() {
        terminal.info("Hello " + name);
    }
}
