package org.apache.maven.surefire.its;

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

import java.util.Collection;

import org.apache.maven.surefire.its.fixture.OutputValidator;
import org.apache.maven.surefire.its.fixture.SurefireJUnit4IntegrationTestCase;
import org.junit.Test;

import static org.apache.commons.lang3.JavaVersion.JAVA_1_8;
import static org.apache.maven.surefire.its.fixture.HelperAssertions.assumeJavaVersion;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Concurrency test for JUnit 5
 *
 * @author Benedikt Ritter
 */
public class JUnit5ConcurrencyIT
    extends SurefireJUnit4IntegrationTestCase
{
    @Test
    public void testJunit5Concurrency()
        throws Exception
    {
        assumeJavaVersion( JAVA_1_8 );

        OutputValidator validator = unpack( "junit5-concurrency" )
            .executeTest()
            .verifyErrorFree( 4 );
        String result = null;
        final Collection<String> lines = validator.loadLogLines();
        for ( String line : lines)
        {
            if ( line.startsWith( "Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed:" ) )
            {
                result = line;
                break;
            }
        }
        assertNotNull( result );
        assertThat( result, anyOf( containsString( "Time elapsed: 1." ), containsString( "Time elapsed: 0.9" ) ) );
        assertThat( result, endsWith( " s - in concurrentjunit47.src.test.java.junit47.BasicTest" ) );
    }
}