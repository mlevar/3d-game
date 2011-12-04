varying vec3 normal;
	
void main()
{
	float intensity1, intensity2;
	vec4 color;
	vec3 n = normalize(normal);
	
	intensity1 = dot(vec3(gl_LightSource[0].position),n);
	
	if (intensity1 > 0.95)
		color = vec4(1.0,1.0,0.0,1.0);
	else if (intensity1 > 0.5)
		color = vec4(0.7,0.7,0.0,1.0);
	else if (intensity1 > 0.25)
		color = vec4(0.4,0.4,0.0,1.0);
	else
		color = vec4(0.2,0.2,0.0,1.0);
	
	gl_FragColor = color;
}