pipeline{
    agent any
    stages{
        stage("installing tomcat& deploy"){
            steps{
                
                sh ''' 
                     IFS=","
                     for serverip in $servers
                     sudo scp -i /tmp/dev_mum.pem tomcatinstall.sh deploy.sh ec2-user@$serverip:/tmp
                     sudo ssh -i /tmp/dev_mum.pem ec2-user@$serverip "chmod +x /tmp/*.sh && bash /tmp/tomcatinstall.sh && bash /tmp/deploy.sh ${jobname} ${branch_name} $build_numbers "
                     '''
            }
        }
    }
}