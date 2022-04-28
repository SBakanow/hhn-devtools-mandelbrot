#type vertex
#version 330 core
layout (location = 0) in vec3 aPos;

void main()
{
    gl_Position = vec4(aPos.xyz, 1.0);
}

#type fragment
#version 330 core

in vec4 gl_FragCoord;

out vec4 frag_color;

int get_iterations()
{
    float real = (gl_FragCoord.x / 1000.0 - 0.5) * 4.0;
    float imag = (gl_FragCoord.y / 1000.0 - 0.07) * 4.0;

    int iterations = 0;
    float const_real = real;
    float const_imag = imag;

    while (iterations < 100)
    {
        float tmp_real = real;
        real = (real * real - imag * imag) + const_real;
        imag = (2.0 * tmp_real * imag) + const_imag;

        float dist = real * real + imag * imag;

        if(dist > 4.0)
        {
            break;
        }
        ++iterations;
    }
    return iterations;
}

vec4 return_color()
{
    int iter = get_iterations();
    if (iter == 100)
    {
        gl_FragDepth = 0.0f;
        return vec4(0.0f, 0.0f, 0.0f, 1.0f);
    }
    float iterations = float(iter) / 100;
    return vec4(0.0f, iterations, 0.0f, 1.0f);
}

void main()
{
    frag_color = return_color();
}