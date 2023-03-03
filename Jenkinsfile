/* Requires the Docker Pipeline plugin */
def params = []

pipeline {
    agent any
        parameters {
            choice(
                name: 'DEPLOY_TYPE',
                choices: ['Simple', 'Advanced'],
                description: 'Select the type of deployment'
            )
            choice(
                name: 'ADVANCED_OPTION',
                choices: [
                    'Option 1\nParam1=Value1\nParam2=Value2',
                    'Option 2\nParam3=Value3\nParam4=Value4'
                ],
                description: 'Select an advanced deployment option',
                when: {
                    expression {
                        params.DEPLOY_TYPE == 'Advanced'
                    }
                }
            )
        }
    stages {
        stage('Run Maven Tests') {
            steps {
                catchError {
                    sh '''
                        rm -rf testng-browserstack # name of your github repository
                        git clone https://github.com/amitsingh-bisht/testng-browserstack # clone your github repository
                        cd testng-browserstack # cd to your repo
                        M2_HOME="/var/lib/jenkins/workspace/apache-maven-3.6.3/bin" # path to your maven
                        export PATH="$M2_HOME:$PATH" # set maven path if it does not exists
                        mvn clean # clean your maven project
                        mvn test # run your tests
                    '''
                }
            }
        }
        stage('Upload Reports in Test Management') {
            steps {
                sh '''
                    export JUNIT_XML_FILE_PATH="/var/lib/jenkins/workspace/TestNG-Pipeline_master/testng-browserstack/target/surefire-reports/junitreports/TEST-com.browserstack.BStackDemoTest.xml"

                    curl -k -X POST https://test-management.browserstack.com/api/v1/import/results/xml/junit \
                    -H "API-TOKEN:$TEST_MANAGEMENT_API_TOKEN" \
                    -F project_name="$TEST_MANAGEMENT_PROJECT_NAME" \
                    -F "file_path=@$JUNIT_XML_FILE_PATH" \
                    -F test_run_name="$TEST_RUN_NAME" \
                    -F user_email=$USER_EMAIL
                '''
            }
        }
    }
    post {
        always {
            junit '/var/lib/jenkins/workspace/TestNG-Pipeline_master/testng-browserstack/target/surefire-reports/junitreports/TEST-com.browserstack.BStackDemoTest.xml'
        }
    }
}