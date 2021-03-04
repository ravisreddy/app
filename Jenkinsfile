node {
   
    def mvnHome = tool 'maven-3.6.1'
    
    stage('Clone and Build') {
      git branch: 'master', url: 'https://github.com/ravisreddy/app.git'          
      mvnHome = tool 'maven-3.6.1'
    	withMaven {
      		sh "mvn clean package"
    	} 
    }    
  
    stage("Test image") {
        steps {
            sh "docker-composer build"
            sh "docker-compose up -d"
        }
    }
}