package org.fugerit.java.tool.postman;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.core.io.SafeIO;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.tool.postman.fun.CollectionEnvMerge;
import org.fugerit.java.tool.util.ArgHelper;

import java.util.Properties;

@Slf4j
public class PostmanTool {

    public static final String ARG_FUN = "fun";

    private static void printHelp() {
        log.info( "help : \n\n{}", SafeIO.readStringStream( () -> ClassHelper.loadFromDefaultClassLoader( "tool-config/help.txt" ) ) );
    }

    public static void main( String[] args ) {
        try {
            Properties params = ArgUtils.getArgs( args );
            ArgHelper.checkAllRequiredThrowRuntimeEx( params, ARG_FUN );
            String fun = params.getProperty( ARG_FUN );
            if (CollectionEnvMerge.class.getSimpleName().equalsIgnoreCase( fun )) {
                CollectionEnvMerge impl = new CollectionEnvMerge();
                impl.accept( params );
            } else {
                throw new ConfigException( "Unsupported fun : "+fun );
            }
        } catch (Exception e) {
            if ( e instanceof ConfigException ) {
                log.warn( "Error : {}", e.getMessage() );
            } else {
                log.error( "Error : "+e, e );
            }
            printHelp();
        }
    }

}
