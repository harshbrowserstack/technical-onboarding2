/* Requires the Docker Pipeline plugin */
def params = []

pipeline {
    agent any
        parameters {
            password(name: 'TEST_MANAGEMENT_API_TOKEN', defaultValue: '****', description: 'API Token of your Test Management Account - You can find here: https://test-management.browserstack.com/settings')
            string(name: 'TEST_MANAGEMENT_PROJECT_NAME', defaultValue: 'Technical Onboarding - Harsh', description: 'Project Name where you want to upload test results, NOTE: If any new project name is defined, Test Management will create a project for you')
            string(name: 'TEST_RUN_NAME', defaultValue: 'Test Run - TestNG cURL - $BUILD_NUMBER', description: 'Name of your Test Run')
            string(name: 'USER_EMAIL', defaultValue: '***', description: 'User Email')
            string(name: 'username', defaultValue: '***', description: 'test email')
            password(name: 'password', defaultValue: '***', description: 'test password')
            
        }
    stages {
        stage('Run Maven Tests') {
            steps {
                 browserstack(credentialsId: 'bb8773bf-c56d-4efa-8122-edebf7b1d3df') {
                    echo "hello"
                    sh '''
                        rm -rf technical-onboarding2 # name of your github repository
                        git clone https://github.com/harshbrowserstack/technical-onboarding2 # clone your github repository
                        cd technical-onboarding2 # cd to your repo
                        M2_HOME="/var/lib/jenkins/workspace/apache-maven-3.6.3/bin" # path to your maven
                        export PATH="$M2_HOME:$PATH" # set maven path if it does not exists
                        mvn clean # clean your maven project
                        mvn test -Dusername=$username -Dpassword=$password
                    '''
                 }
               
            }
        }
        stage('Upload Reports in Test Management') {
            steps {
                sh '''
                    export JUNIT_XML_FILE_PATH="$(pwd)/technical-onboarding2/target/surefire-reports/junitreports/TEST-com.browserstack.BStackDemoTest.xml"

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
}
