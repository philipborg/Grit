#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform mat4 u_projTrans;

void main() {
		float height = 100;
		v_texCoords = v_texCoords + vec2(0,0);
        vec3 color = texture2D(u_texture, v_texCoords).rgb;
        float alpha = texture2D(u_texture, v_texCoords+vec2(0,height)).a;
        float black = 0;
        vec3 grayscale = vec3(black);

        gl_FragColor = vec4(grayscale, texture2D(u_texture, v_texCoords).a - 0.5);
}