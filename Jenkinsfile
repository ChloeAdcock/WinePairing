pipeline {
   agent any

   tools {
      maven 'maven'
   }

   stages {
      stage('Build') {
         steps {
            git 'https://github.com/ChloeAdcock/WinePairing.git'

            sh "mvn clean package"
            sh "mvn clean deploy"
         }

         post {
            success {
               archiveArtifacts 'build/target/*.jar'
            }
         }
      }
   }
}
