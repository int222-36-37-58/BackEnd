pipeline {
    agent any
    
     tools {
    	nodejs "nodejs"
    }
    
    stages {
      
        stage('stop and remove container, image') {
            steps {
                script {
                    def imageExists = sh(script: 'docker images -q backend', returnStdout: true) == ""
                    println imageExists
                    
                    if( !imageExists ){
                           sh 'docker stop backend'
                           sh 'docker rm backend'
                           sh 'docker image rm backend'
                    }else {
                        echo 'Skip this stage '
                    }
                }
            }
        }
      
        stage('remove whole data') {
            steps {   
                sh 'rm -rf *'
            }
        }
        
        stage('git clone') {
            steps {       
                git branch: 'master',
                    credentialsId: 'punthanatGit',
                    url: 'https://github.com/int222-36-37-58/BackEnd.git'
            }
        }  
        
        stage('deploy dev') {
            steps {
                sh 'docker-compose up -d'
            }
        }
        
        stage('test') {
            steps {
                sh 'node --version '
                sh 'npm --version '
                sh 'npm install -g newman'
                sh 'pwd'
                sh 'newman run https://www.getpostman.com/collections/ad108bb37c9ad912828c'
            }
        }
        
        stage('fake deploy production') {
            steps {
                echo 'deployyy!!!!'
            }
        }
      
    }
}