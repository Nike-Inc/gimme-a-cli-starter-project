/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.mycli.commands;

import com.beust.jcommander.Parameters;
import com.nike.gimme.a.cli.Command;
import com.nike.gimme.a.cli.Terminal;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Command that outputs the current version of this CLI.
 */
@Component
@Parameters(commandNames = {"--version"}, commandDescription = "Outputs this CLI's version")
public class VersionCommand implements Command {

    private static final String PROPERTIES_FILE = "mycli.properties";
    private static final String VERSION_PROP_NAME = "mycli.version";

    private Terminal terminal;

    @Autowired
    public VersionCommand(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void execute() throws Exception {
        terminal.info(getVersion());
    }

    /**
     * Get a string describing the current version of this CLI
     */
    private String getVersion() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(VERSION_PROP_NAME);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
