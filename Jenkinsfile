pipeline {
    agent any

    stages {
        stage('Checkout code') {
            steps {
                git branch: 'main', url: 'https://github.com/pilo77/Prueba-tecnica'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the app...'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }

    post {
        success {
            script {
                if (isUnix()) {
                    sh 'echo "Build successful"'
                } else {
                    bat 'echo Build successful'
                }
            }
        }

        failure {
            script {
                if (isUnix()) {
                    sh 'echo "Build failed"'
                } else {
                    bat 'echo Build failed'
                }
            }
        }
    }
}

