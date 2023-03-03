/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh '
                    pwd
                    ls
                    cd testng-browserstack
                    pwd
                    ls
                    M2_HOME="/var/lib/jenkins/workspace/apache-maven-3.6.3/bin"
                    export PATH="$M2_HOME:$PATH"

                    mvn clean
                    mvn test
                '
            }
        }
    }
}