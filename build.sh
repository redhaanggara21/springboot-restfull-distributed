
#!/bin/bash
export BASE_PATH=/home/ubuntu/docker/spring
export HELM_PATH=/home/ubuntu/helm/spring
if [ -z "$1" ]
        then
        echo "#################################################"        
        echo "# please add environment argument !!            #"
        echo "# =================================             #"
        echo "# sh scriptname.sh dev or sh scriptname.sh prod #"
        echo "#################################################"
else
        export ENV=$1
        define_env()
        {
                export NAMESPACE=dts-$ENV
                export KUBECONFIG=/home/ubuntu/.kube/config_$ENV

                #doctl auth switch --context $ENV
        }

        pull_git()
        {
                cd $BASE_PATH/signature/
                STATUS=`git pull`
                export TAG_SIGNATURE=`git rev-parse --short HEAD`
		echo $STATUS
                echo "signature Commit ID: "$TAG_SIGNATURE

                if [ "$STATUS" = "Already up to date." ]; then
                        if [ "$ENV" = "prod" ]; then
                                echo "Deploying Process"
                                echo =================================
                                deploy

                        else
                                echo "no need Deploy on development environment"
                                #remember to take out this once real

                    fi

                else
                        echo "Build Docker Image"
                        echo =================================
                        build_docker

                        echo "Push Docker Image"
                        echo =================================
                        push_docker

                        echo "Deploying Process"
                        echo =================================
			deploy
		 fi

        }

        build_docker()
        {
                cd  $BASE_PATH
                docker build --no-cache -t redhaanggara21/signature:$TAG_SIGNATURE .

        }

        push_docker()
        {
                docker push redhaanggara21/signature:$TAG_SIGNATURE
        }

        deploy()
        {
                cd $HELM_PATH
                if [ "$ENV" = "prod" ]; then
                        helm upgrade --install --atomic --timeout 2m0s --set image.tag=${TAG_SIGNATURE} --namespace  $NAMESPACE signature . 
                 else
                        helm upgrade --install --atomic --timeout 2m0s --set replicaCount=1 --set image.tag=${TAG_SIGNATURE} --namespace  $NAMESPACE signature . 
                fi
        }

        echo "define Environment Variables:"
        echo =================================
        define_env
        echo ""
        echo "pulling repo from git:"
        echo =================================
        pull_git
fi
