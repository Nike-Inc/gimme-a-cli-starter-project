/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.mycli;

import com.nike.gimme.a.cli.Config;
import com.nike.gimme.a.cli.GimmeACli;

/**
 * Main for the CLI
 */
public class Main {

    public static void main(String[] args) {
        new GimmeACli(
                Config.builder()
                        .withCliName("mycli") // name of your CLI
                        .withPackagesToScan("com.nike") // packages to scan both for Commands and Spring components.
                        .build()
        ).run(args);
    }
}