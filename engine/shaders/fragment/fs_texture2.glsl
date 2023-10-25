#version 330 core

in vec3 aPos;
in vec3 aNormal;
in vec2 aTexCoord;
in vec2 aOffsetTexCoord;

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
uniform Light light2;

struct SpotLight {
  vec3 position;
  vec3 ambient;
  vec3 diffuse;
  vec3 specular;

  vec3 direction;
  float outerCutOff;
  float cutOff;

  float constant;
  float linear;
  float quadratic;
};

uniform SpotLight spotLight;

struct Material {
  vec3 ambient;
  vec3 diffuse;
  vec3 specular;
  float shininess;
};

uniform Material material;

vec3 calcLight(Light light, vec3 textureVec)
{
  // ambient
  vec3 ambient = light.ambient * textureVec;

  // diffuse
  vec3 norm = normalize(aNormal);
  vec3 lightDir = normalize(light.position - aPos);
  float diff = max(dot(norm, lightDir), 0.0);
  vec3 diffuse = light.diffuse * (diff * textureVec.rgb);

  // specular
  vec3 viewDir = normalize(viewPos - aPos);
  vec3 reflectDir = reflect(-lightDir, norm);
  float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
  vec3 specular = light.specular * (spec * material.specular);

  vec3 result = ambient + diffuse + specular;

  return result;
}

vec3 calcSpotLight(SpotLight spotLight, vec3 textureVec)
{
  vec3 lightDir = normalize(spotLight.position - aPos);
  // diffuse shading
  float diff = max(dot(aNormal, lightDir), 0.0);
  // specular shading
  vec3 reflectDir = reflect(-lightDir, aNormal);
  float spec = pow(max(dot(lightDir, reflectDir), 0.0), material.shininess);
  // attenuation
  float distance = length(spotLight.position - aPos);
  float attenuation = 1.0 / (spotLight.constant + spotLight.linear * distance + spotLight.quadratic * (distance * distance));
  // spotlight intensity
  float theta = dot(lightDir, normalize(-spotLight.direction));
  float epsilon = spotLight.cutOff - spotLight.outerCutOff;
  float intensity = clamp((theta - spotLight.outerCutOff) / epsilon, 0.0, 1.0);
  // combine results
  vec3 ambient = spotLight.ambient;
  vec3 diffuse = spotLight.diffuse * diff;
  vec3 specular = spotLight.specular * spec;
  ambient *= attenuation * intensity;
  diffuse *= attenuation * intensity;
  specular *= attenuation * intensity;

  vec3 result = ambient + diffuse + specular;

  return result;
}

void main() {

  vec4 first = texture(first_texture, aTexCoord);
  vec4 second = texture(second_texture,  aOffsetTexCoord * -1);

  vec3 result = calcLight(light, vec3(mix(first, second, 1.0f).rgb));
  result = result + calcLight(light2, vec3(mix(first, second, 1.0f).rgb));

  fragColor = vec4(result, 1.0);

}