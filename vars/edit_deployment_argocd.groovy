def call(String image_name) {
  withCredentials([usernamePassword(
    credentialsId: '743c416b-fd33-4994-be23-6c1599d6e213', // Update to your actual Jenkins credential ID
    usernameVariable: 'GIT_USER',
    passwordVariable: 'GIT_TOKEN'
  )]) {
    sh '''
      git clone https://github.com/YoussefAzozz/argocd-lab.git
    ''''
    dir('argocd-lab') {
    sh '''
    # Modify the image line in deployment.yml
    sed -i 's|image:.*|image: "${image_name}:${BUILD_NUMBER}"|g' deployment.yml
    cat deployment.yml
    '''
  }
  }
}
