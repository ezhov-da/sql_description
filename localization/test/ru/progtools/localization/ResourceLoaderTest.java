package ru.progtools.localization;

import org.junit.Test;

/**
 *
 * @author deonisius
 */
public class ResourceLoaderTest {
    
    public ResourceLoaderTest() {
    }

    @Test
    public void testSomeMethod() {
        ResourceLoader resourceLoader = ResourceLoader.getInstance();
        resourceLoader.load(null);
        System.out.println(resourceLoader.getString(LocalKeys.TITLE_COMMON_LOCAL_JS));
    }
    
}
