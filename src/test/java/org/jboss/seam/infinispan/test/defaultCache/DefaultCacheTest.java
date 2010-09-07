package org.jboss.seam.infinispan.test.defaultCache;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.infinispan.AdvancedCache;
import org.infinispan.Cache;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.infinispan.Infinispan;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests that the default cache is available
 * 
 * @author Pete Muir
 *
 */
@RunWith(Arquillian.class)
public class DefaultCacheTest
{
   
   @Deployment
   public static Archive<?> deployment()
   {
      return ShrinkWrap.create(JavaArchive.class, "test.jar")
      .addPackage(DefaultCacheTest.class.getPackage())
      .addPackage(Infinispan.class.getPackage());
   }
   
   /**
    * The default cache can be injected with no configuration
    */
   @Inject
   private Cache<String, String> cache;
   
   @Inject
   private AdvancedCache<String, String> advancedCache;
   
   @Test
   public void testDefaultCache()
   {
      // Simple test to make sure the default cache works
      cache.put("pete", "British");
      cache.put("manik", "Sri Lankan");
      assertEquals("British", cache.get("pete"));
      assertEquals("Sri Lankan", cache.get("manik"));
      // Check that the advanced cache contains the same data as the simple cache
      assertEquals("British", advancedCache.get("pete"));
      assertEquals("Sri Lankan", advancedCache.get("manik"));
   }

}
