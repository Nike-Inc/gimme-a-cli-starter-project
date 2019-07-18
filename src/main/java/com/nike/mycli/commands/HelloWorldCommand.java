/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
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
