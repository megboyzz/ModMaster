package ru.runner.utli

import java.io.IOException
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Modifier
import java.net.JarURLConnection
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.Attributes


class JarClassLoader(url: URL) : URLClassLoader(arrayOf<URL>(url)) {
    private val url: URL = url

    @get:Throws(IOException::class)
    val mainClassName: String?
        /**
         * Returns the name of the jar file main class, or null if
         * no "Main-Class" manifest attributes was defined.
         */
        get() {
            val u = URL("jar", "", "$url!/")
            val uc = u.openConnection() as JarURLConnection

            uc.content

            val attr = uc.getMainAttributes()
            return attr.getValue(Attributes.Name.MAIN_CLASS)
        }

    /**
     * Invokes the application in this jar file given the name of the
     * main class and an array of arguments. The class must define a
     * static method "main" which takes an array of String arguemtns
     * and is of return type "void".
     *
     * @param name the name of the main class
     * @param args the arguments for the application
     * @exception ClassNotFoundException if the specified class could not
     * be found
     * @exception NoSuchMethodException if the specified class does not
     * contain a "main" method
     * @exception InvocationTargetException if the application raised an
     * exception
     */
    @Throws(ClassNotFoundException::class, NoSuchMethodException::class, InvocationTargetException::class)
    fun invokeClass(name: String?, args: Array<String?>) {
        val c = loadClass(name)
        val m = c.getMethod("main", *arrayOf<Class<*>>(args.javaClass))
        m.setAccessible(true)
        val mods: Int = m.modifiers
        if (m.returnType !== Void.TYPE || !Modifier.isStatic(mods) ||
            !Modifier.isPublic(mods)
        ) {
            throw NoSuchMethodException("main")
        }
        try {
            m.invoke(null, arrayOf<Any>(args))
        } catch (e: IllegalAccessException) {
            // This should not happen, as we have disabled access checks
        }
    }
}