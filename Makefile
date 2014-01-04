clean: 
	gradle --daemon clean


publish:
	gradle --daemon publish

s3sync:
	s3cmd sync -P build/repo/ s3://koobe-repo/maven/private/
