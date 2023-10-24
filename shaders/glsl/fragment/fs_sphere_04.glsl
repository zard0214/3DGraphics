#version 330 core

in vec3 aPos;
in vec3 aNormal;
in vec2 aTexCoord;

out vec4 fragColor;
 
uniform vec3 viewPos;
uniform sampler2D first_texture;
uniform sampler2D second_texture;

struct Light {
  vec3 position;
  vec3 ambient;
  vec3 diffuse;
  vec3 specular;
};

uniform Light light;
uniform Light light_2;

struct Material {
  vec3 ambient;
  vec3 diffuse;
  vec3 specular;
  float shininess;
}; 
  
uniform Material material;

void main() {

  vec3 norm = normalize(aNormal);
  vec3 viewDir = normalize(viewPos - aPos);

  // ambient
  vec3 ambient = light.ambient * vec3(texture(first_texture, aTexCoord));

  // diffuse
  vec3 lightDir = normalize(light.position - aPos);  
  float diff = max(dot(norm, lightDir), 0.0);
  vec3 diffuse = light.diffuse * diff * vec3(texture(first_texture, aTexCoord)); 

  // specular
  vec3 reflectDir = reflect(-lightDir, norm);  
  float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
  vec3 specular = light.specular * spec * vec3(texture(second_texture, aTexCoord));

  vec3 result = ambient + diffuse + specular;

  // ambient
  vec3 ambient_2 = light_2.ambient * vec3(texture(first_texture, aTexCoord));

  // diffuse
  vec3 lightDir_2 = normalize(light_2.position - aPos);
  float diff_2 = max(dot(norm, lightDir_2), 0.0);
  vec3 diffuse_2 = light_2.diffuse * diff_2 * vec3(texture(first_texture, aTexCoord));

  // specular
  vec3 reflectDir_2 = reflect(-lightDir_2, norm);
  float spec_2 = pow(max(dot(viewDir, reflectDir_2), 0.0), material.shininess);
  vec3 specular_2 = light_2.specular * spec_2 * vec3(texture(second_texture, aTexCoord));

  vec3 result_2 = result + ambient_2 + diffuse_2 + specular_2;

  fragColor = vec4(result_2, 1.0f);
}