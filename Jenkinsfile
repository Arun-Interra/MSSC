pipeline {    
    agent { 
        label 'java_maven'
    }    
    
    options {       
        buildDiscarder(            
            logRotator(numToKeepStr:'20')        
            )        
    }

    parameters {
        text(name: 'BRANCH', description: 'Branch name', defaultValue: 'develop')
    }

    environment{
        JAVA_HOME="/app/jdk1.8.0_181"
        PATH="$PATH:$JAVA_HOME/bin"
        DOCKERCRED=credentials('dockerhub')		
        DOCKER_USERNAME = "${DOCKERCRED_USR}"		
        DOCKER_PASSWORD = "${DOCKERCRED_PSW}"  
        BUILD_NUMBER = "${env.BUILD_NUMBER}"  
        
    }

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: params.BRANCH, url: 'git@x1gita01.mnao.net:Mazda-Microservices/MSSC_MSVC.git'
            }
        }
        
        stage('Test') {
            steps{
                sh '''
                    echo "JAVA_HOME: $JAVA_HOME"
                    chmod +x ./mvnw
                   # ./mvnw test
                '''
            }
        }
        
        stage('Build') {
            steps{
                sh '''
                    ./mvnw clean package spring-boot:repackage -DskipTests
                '''
            }
        }
        
        stage('Docker Build'){
            steps{
                sh '''
                    echo "Docker build"
                    docker build -t mazdadockertest/msscmsvc:${BUILD_NUMBER} .
                    docker tag mazdadockertest/msscmsvc:${BUILD_NUMBER} mazdadockertest/msscmsvc:latest
                '''
            }
        }
        
        stage('Docker Publish'){
            steps{
                sh '''
                    echo "Docker image push to docker hub"
                    docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}
                    docker push mazdadockertest/msscmsvc:${BUILD_NUMBER}					
			        docker push mazdadockertest/msscmsvc:latest
                '''
            }
        }
        
        stage('Clean workspace'){
            steps{
                sh '''
                    rm -rf * 2>/dev/null
                    docker rmi mazdadockertest/msscmsvc:${BUILD_NUMBER}
                '''
            }
        }
    }
}
