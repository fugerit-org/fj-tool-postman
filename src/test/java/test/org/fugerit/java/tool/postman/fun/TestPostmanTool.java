package test.org.fugerit.java.tool.postman.fun;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.tool.postman.PostmanTool;
import org.fugerit.java.tool.postman.fun.CollectionEnvMerge;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestPostmanTool {

    private static final String TARGET = "target";

    @Test
    public void testMain1() throws ConfigException, IOException {
        File outputCollection = new File( TARGET, "test_main_1.json" );
        String[] args = {
                ArgUtils.getArgString(PostmanTool.ARG_FUN ), CollectionEnvMerge.class.getSimpleName(),
                ArgUtils.getArgString( CollectionEnvMerge.ARG_COLLECTION_PATH ), "src/test/resources/sample/fj-doc-playground-quarkus.postman_collection.json",
                ArgUtils.getArgString( CollectionEnvMerge.ARG_ENVIRONMENT_PATH ), "src/test/resources/sample/fugerit_ampere04.postman_environment.json",
                ArgUtils.getArgString( CollectionEnvMerge.ARG_OUTPUT_PATH ), outputCollection.getCanonicalPath()
        };
        PostmanTool.main( args );
        Assert.assertTrue( outputCollection.exists() );
    }

    @Test
    public void testMain2KO() throws ConfigException, IOException {
        File outputCollection = new File( TARGET, "test_main_2.json" );
        String[] args = {
                ArgUtils.getArgString(PostmanTool.ARG_FUN ), CollectionEnvMerge.class.getSimpleName()+"_UNKNOWN",
                ArgUtils.getArgString( CollectionEnvMerge.ARG_COLLECTION_PATH ), "src/test/resources/sample/fj-doc-playground-quarkus.postman_collection.json",
                ArgUtils.getArgString( CollectionEnvMerge.ARG_ENVIRONMENT_PATH ), "src/test/resources/sample/fugerit_ampere04.postman_environment.json",
                ArgUtils.getArgString( CollectionEnvMerge.ARG_OUTPUT_PATH ), outputCollection.getCanonicalPath()
        };
        PostmanTool.main( args );
        Assert.assertFalse( outputCollection.exists() );
    }

    @Test
    public void testMain3KO() throws ConfigException, IOException {
        File outputCollection = new File( TARGET, "test_main_3.json" );
        String[] args = {
                ArgUtils.getArgString(PostmanTool.ARG_FUN ), CollectionEnvMerge.class.getSimpleName(),
                ArgUtils.getArgString( CollectionEnvMerge.ARG_COLLECTION_PATH ), "src/test/resources/sample/fj-doc-playground-quarkus.postman_collection.json",
                ArgUtils.getArgString( CollectionEnvMerge.ARG_OUTPUT_PATH ), outputCollection.getCanonicalPath()
        };
        PostmanTool.main( args );
        Assert.assertFalse( outputCollection.exists() );
    }

}
