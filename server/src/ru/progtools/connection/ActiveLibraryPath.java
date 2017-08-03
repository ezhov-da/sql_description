package ru.progtools.connection;

import java.io.File;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс, который устанавливает "java.library.path"
 * <p>
 * @author ezhov_da
 */
public class ActiveLibraryPath
{
    private static final Logger LOG = Logger.getLogger(ActiveLibraryPath.class.getName());

    public static final synchronized void setLibraryPath(String path)
    {
        try
        {
            System.setProperty("java.library.path", path);
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } catch (Exception ex)
        {
            LOG.log(Level.SEVERE, "error load library path", ex);
        }
    }

    public static final synchronized void setPath()
    {
        LOG.info("устанавливаем пути dll");
        String libx64 = "dll/x64";
        LOG.log(Level.INFO, "64: {0}", libx64);
        String libx86 = "dll/x86";
        LOG.log(Level.INFO, "86: {0}", libx86);
        File filex64 = new File(libx64);
        File filex86 = new File(libx86);
        String ocArch = System.getProperty("sun.arch.data.model");
        String fullPath = null;
        if (null != ocArch)
        {
            switch (ocArch)
            {
                case "64":
                    fullPath = filex64.getAbsolutePath();
                    LOG.log(Level.INFO, "64: {0}", fullPath);
                    break;
                case "32":
                    fullPath = filex86.getAbsolutePath();
                    LOG.log(Level.INFO, "32: {0}", fullPath);
                    break;
            }
        }
        setLibraryPath(fullPath);
    }
}
