package ca.usherbrooke.ift232.actuRSS.modelTest;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.model.Model;

import org.w3c.dom.Document;

public class ModelTest extends TestCase {

        @Test
        public void testModel()  {
            fail("Not yet implemented");
        }

        @Test
        public void testSynchronize() {
            fail("Not yet implemented");
        }

        @Test
        public void testNotifyObserver() {
            fail("Not yet implemented");
        }

        @Test
        public void testObtainDocument() {
            String feedurl = "http://feeds2.feedburner.com/Pressecitron";
            assertTrue(Model.obtainDocument(feedurl) instanceof Document);
        }

}
