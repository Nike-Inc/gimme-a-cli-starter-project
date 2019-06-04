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
