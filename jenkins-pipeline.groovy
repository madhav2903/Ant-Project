pipeline {
    agent any
    stages {
        stage('check'){
            steps {
        git credentialsId: 'Github', url: 'https://github.com/madhav2903/Ant-Project.git'
            }
        }
        stage('build'){
            steps {
                sh 'ant'
            }
        }
        stage('executeSonarqubeReport')
        {
            steps {
                sh "ant run sonar"
            }
        }
        stage('deploy')
        {
             steps {
               sh "deploy adapters: [tomcat9(credentialsId: 'TomJen', path: '', url: 'http://localhost:8081// opt/tomcat/webapps/')], contextPath: 'null', war: '**/*.jar'"
        }
    }
  }
}
