/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    stages {
        stage('Run Maven Tests') {
            steps {
                sh '''
                    rm -rf testng-browserstack
                    git clone https://github.com/amitsingh-bisht/testng-browserstack
                    cd testng-browserstack
                    M2_HOME="/var/lib/jenkins/workspace/apache-maven-3.6.3/bin"
                    export PATH="$M2_HOME:$PATH"
                    mvn clean
                    mvn test
                '''
            }
        }
        stage('Upload Result to Test Management') {
            steps {
                sh '''
                    export TEST_MANAGEMENT_API_TOKEN="943d7c85-9497-4ba1-88ac-af7642828a42"
                    export TEST_MANAGEMENT_PROJECT_NAME="XYZ Banking Corporation"
                    export JUNIT_XML_FILE_PATH="/var/lib/jenkins/workspace/$JOB_BASE_NAME/testng-browserstack/target/surefire-reports/junitreports/TEST-com.browserstack.BStackDemoTest.xml"
                    export TEST_RUN_NAME="Test Run - TestNG cURL - $BUILD_NUMBER"
                    export USER_EMAIL="test.management23@gmail.com"

                    curl -k -X POST https://test-management.browserstack.com/api/v1/import/results/xml/junit \
                    -H "API-TOKEN:$TEST_MANAGEMENT_API_TOKEN" \
                    -F project_name=$TEST_MANAGEMENT_PROJECT_NAME \
                    -F "file_path=@$JUNIT_XML_FILE_PATH" \
                    -F test_run_name="$TEST_RUN_NAME" \
                    -F user_email=$USER_EMAIL
                '''
            }
        }
    }
}