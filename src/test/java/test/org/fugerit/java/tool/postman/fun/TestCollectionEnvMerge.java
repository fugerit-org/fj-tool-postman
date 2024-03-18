package test.org.fugerit.java.tool.postman.fun;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.tool.postman.fun.CollectionEnvMerge;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class TestCollectionEnvMerge {

    private static final CollectionEnvMerge MERGE = new CollectionEnvMerge();

    private static final String TARGET = "target";

    @Test
    public void testMergeNormal() throws ConfigException, IOException {
        Properties params = new Properties();
        File outputFile = new File( TARGET, "collection_environment_merge_normal.json" );
        log.info( "delete : {} - {}", outputFile, outputFile.delete());
        params.setProperty(CollectionEnvMerge.ARG_COLLECTION_PATH, "src/test/resources/sample/fj-doc-playground-quarkus.postman_collection.json" );
        params.setProperty(CollectionEnvMerge.ARG_ENVIRONMENT_PATH, "src/test/resources/sample/fugerit_ampere04.postman_environment.json" );
        params.setProperty(CollectionEnvMerge.ARG_OUTPUT_PATH, outputFile.getCanonicalPath() );
        MERGE.accept( params );
        Assert.assertTrue( outputFile.exists() );
    }

    @Test
    public void testMergeVars() throws ConfigException, IOException  {
        Properties params = new Properties();
        File outputFile = new File( TARGET, "collection_environment_merge_vars.json" );
        log.info( "delete : {} - {}", outputFile, outputFile.delete());
        params.setProperty(CollectionEnvMerge.ARG_COLLECTION_PATH, "src/test/resources/sample/fj-doc-playground-quarkus-var.postman_collection.json" );
        params.setProperty(CollectionEnvMerge.ARG_ENVIRONMENT_PATH, "src/test/resources/sample/fugerit_local.postman_environment.json" );
        params.setProperty(CollectionEnvMerge.ARG_OUTPUT_PATH, outputFile.getCanonicalPath() );
        MERGE.accept( params );
        Assert.assertTrue( outputFile.exists() );
    }

}
