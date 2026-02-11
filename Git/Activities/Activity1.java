
pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java/'
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from Git repository
                git branch: 'master', url: 'https://your-git-repo-url.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running TestNG tests...'
                sh 'mvn test'
            }
            post {
                always {
                    // Publish TestNG results
                    publishHTML([allowMissing: false,
                                 alwaysLinkToLastBuild: true,
                                 keepAll: true,
                                 reportDir: 'target/surefire-reports',
                                 reportFiles: 'index.html',
                                 reportName: 'TestNG Report'])
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
