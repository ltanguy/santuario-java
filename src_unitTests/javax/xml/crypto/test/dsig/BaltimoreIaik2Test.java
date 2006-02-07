/*
 * Copyright 2006 The Apache Software Foundation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
/*
 * Copyright 2005 Sun Microsystems, Inc. All rights reserved.
 */
package javax.xml.crypto.test.dsig;

import java.io.File;
import java.security.Security;

import junit.framework.*;

import javax.xml.crypto.test.KeySelectors;

/**
 * This is a testcase to validate all "ec-merlin-iaikTests-two" 
 * testcases from Baltimore
 *
 * @author Sean Mullan
 */
public class BaltimoreIaik2Test extends TestCase {

    private SignatureValidator validator;
    private File dir;

    static {
        Security.insertProviderAt
            (new org.jcp.xml.dsig.internal.dom.XMLDSigRI(), 1);
    }

    public BaltimoreIaik2Test(String name) {
        super(name);
	String fs = System.getProperty("file.separator");
	dir = new File(System.getProperty("basedir") + fs + "data" + fs +
	    "ie" + fs + "baltimore" + fs + "merlin-examples",
            "ec-merlin-iaikTests-two");
	validator = new SignatureValidator(dir);
    }

    public void test_signature() throws Exception {
        String file = "signature.xml";
	boolean coreValidity = validator.validate
	    (file, new KeySelectors.KeyValueKeySelector());
        assertTrue("Signature failed core validation", coreValidity);
    }    

    public static void main(String[] args) throws Exception {
        BaltimoreIaik2Test bt = new BaltimoreIaik2Test("");
	bt.test_signature();
    }
}
