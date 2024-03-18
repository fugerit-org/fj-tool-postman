package org.fugerit.java.tool.postman.fun;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.function.UnsafeConsumer;
import org.fugerit.java.tool.util.ArgHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

@Slf4j
public class CollectionEnvMerge implements UnsafeConsumer<Properties, ConfigException> {

    public static final String ARG_COLLECTION_PATH = "collection-path";

    public static final String ARG_ENVIRONMENT_PATH = "environment-path";

    public static final String ARG_OUTPUT_PATH = "output-path";

    private static final String LOG_FILE_PATTERN = "{} -> {} -> exists?:{}";

    private static final String ATT_VARIABLE = "variable";

    private static final String ATT_VALUES = "values";

    @SuppressWarnings("unchecked")
    private void handle(LinkedHashMap<String, Object> jsonCollection, LinkedHashMap<String, Object> jsonEnvironment, File outputFile, ObjectMapper mapper ) throws IOException {
        List<Object> collectionsVars = (List<Object>) jsonCollection.get( ATT_VARIABLE );
        if ( collectionsVars == null ) {
            collectionsVars = new ArrayList<>();
        }
        List<Object> environmentVars = (List<Object>) jsonEnvironment.get( ATT_VALUES );
        if ( environmentVars == null ) {
            log.info( "No variables set in environment" );
        } else {
            collectionsVars.addAll( environmentVars );
        }
        jsonCollection.put( ATT_VARIABLE, collectionsVars );
        try (FileWriter writer = new FileWriter( outputFile )) {
            mapper.writerWithDefaultPrettyPrinter().writeValue( writer, jsonCollection );
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void accept(Properties params) throws ConfigException {
        // check required parameters
        ArgHelper.checkAllRequiredThrowRuntimeEx( params, ARG_COLLECTION_PATH, ARG_ENVIRONMENT_PATH, ARG_OUTPUT_PATH );
        ConfigException.apply( () -> {
            String collectionPath = params.getProperty( ARG_COLLECTION_PATH );
            String environmentPath = params.getProperty( ARG_ENVIRONMENT_PATH );
            String outputPath = params.getProperty( ARG_OUTPUT_PATH );
            File collectionFile = new File( collectionPath );
            File environmentFile = new File( environmentPath );
            File outputFile = new File( outputPath );
            log.info( LOG_FILE_PATTERN, ARG_COLLECTION_PATH, collectionFile.getAbsolutePath(), collectionFile.exists() );
            log.info( LOG_FILE_PATTERN, ARG_ENVIRONMENT_PATH, environmentFile.getAbsolutePath(), environmentFile.exists() );
            log.info( LOG_FILE_PATTERN, ARG_OUTPUT_PATH, outputFile.getAbsolutePath(), outputFile.exists() );
            if ( collectionFile.exists() && environmentFile.exists() ) {
                ObjectMapper mapper = new ObjectMapper();
                LinkedHashMap<String, Object> jsonCollection = mapper.readValue( collectionFile, LinkedHashMap.class);
                LinkedHashMap<String, Object> jsonEnvironment = mapper.readValue( environmentFile, LinkedHashMap.class);
                this.handle( jsonCollection, jsonEnvironment, outputFile, mapper );
            } else {
                throw new ConfigRuntimeException( "collection and environment to merge must both exists" );
            }
        } );
    }

}
