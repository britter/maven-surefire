package org.apache.maven.surefire.its.jiras;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.surefire.its.fixture.SurefireIntegrationTestCase;
import org.apache.maven.surefire.its.fixture.SurefireLauncher;

/**
 * @author Kristian Rosenvold
 */
public class Surefire747MethodParallelWithSuiteCountIT
    extends SurefireIntegrationTestCase
{

    public void testMethodsParallelWithSuite()
    {
        unpack().executeTest().verifyErrorFree( 6 );
    }

    public void testClassesParallelWithSuite()
    {
        unpack().parallelClasses().executeTest().verifyErrorFree( 6 );
    }

    public SurefireLauncher unpack()
    {
        return unpack( "junit47-parallel-with-suite" );
    }

}