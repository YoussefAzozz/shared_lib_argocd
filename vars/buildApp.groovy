def call() {
    git branch: 'main', url: 'https://github.com/Ibrahim-Adel15/Jenkins_App.git'
    sh 'mvn clean package -DskipTests'
}
