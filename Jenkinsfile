pipeline {
    agent any
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
                    url: 'https://github.com/int222-36-37-58/backend.git'
            }
        }  
        
        stage('(deploy) start contianer') {
            steps {
                sh 'docker-compose up -d'
            }
        }
      
    }
}