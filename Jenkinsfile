pipeline {
   agent any

   tools {
      maven 'maven'
   }

   stages {
      stage('Build') {
         steps {
            git 'https://github.com/ChloeAdcock/WinePairing.git'

            sh "mvn -Dmaven.test.failure.ignore=true clean package"
         }

         post {
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
               archiveArtifacts 'target/*.jar'
            }
         }
      }
   }
}
