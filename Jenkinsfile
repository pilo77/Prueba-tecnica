pipeline {
    agent any
    stages {
        stage('Checkout code') {
            steps {
                git 'https://github.com/pilo77/Prueba-tecnica'
            }
        }
        stage('Build') {
            steps {
                sh 'echo "building the app"'
            }
        }
        stage('Test') {
            steps {
                sh 'echo "Running tests"'
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "deploying"'
            }
        }
    }
}
