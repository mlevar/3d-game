import static org.lwjgl.opengl.ARBBufferObject.*;
import static org.lwjgl.opengl.ARBVertexBufferObject.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.*;

import org.lwjgl.opengl.GL11;

public class Pyramid extends Model3D
{
  private static int BYTE_SIZE = 4;
  private static int VERTEX_DIMENSION = 3;
  private static int COLOR_DIMENSION = 4;

  private static int verticesBufferID;
  private static int indicesBufferID;
  private static int colorBufferID;
  private static FloatBuffer verticesBuffer;
  private static FloatBuffer colorBuffer;
  private static IntBuffer indicesBuffer;
  private static ByteBuffer mappedBuffer;
  private static ByteBuffer mappedIndicesBuffer;
  private static IntBuffer mappedIndicesIntBuffer;
  private static FloatBuffer mappedFloatBuffer;

  float[] vertices;
  int[] indices;
  float[] colors;

  public Pyramid()
  {
    // Vertex, index and color float arrays (We do same things for normals and
    // tex coords)
    vertices = new float[]
    { -1, -1, 0, 1, -1, 1, 1, 1, 0, 1, -1, -1 };
    indices = new int[]
    { 0, 1, 2, 2, 1, 3, 0, 2, 3, 0, 3, 1 };
    colors = new float[]
    { 1f, 0f, 0f, 1.0f, 0f, 1f, 0f, 1.0f, 0f, 0f, 1f, 1.0f, 1f, 1f, 0f, 1.0f };

    // Generating buffers
    verticesBufferID = glGenBuffersARB();
    colorBufferID = glGenBuffersARB();
    indicesBufferID = glGenBuffersARB();

    // Binding buffers
    glBindBufferARB(GL_ARRAY_BUFFER_ARB, verticesBufferID);
    glBindBufferARB(GL_ARRAY_BUFFER_ARB, colorBufferID);
    glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, indicesBufferID);

    // Creating buffers for the actual data
    verticesBuffer = ByteBuffer.allocateDirect(vertices.length * BYTE_SIZE)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();
    verticesBuffer.put(vertices);
    verticesBuffer.rewind();

    colorBuffer = ByteBuffer.allocateDirect(colors.length * BYTE_SIZE)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();
    colorBuffer.put(colors);
    colorBuffer.rewind();

    indicesBuffer = ByteBuffer.allocateDirect(indices.length * BYTE_SIZE)
        .order(ByteOrder.nativeOrder()).asIntBuffer();
    indicesBuffer.put(indices);
    indicesBuffer.rewind();

    // Copying initialized buffers to memory (there is data in Buffers already)
    glBufferDataARB(GL_ARRAY_BUFFER_ARB, (vertices.length + colors.length)
        * BYTE_SIZE, GL_STREAM_DRAW_ARB);
    glBufferDataARB(GL_ELEMENT_ARRAY_BUFFER_ARB, indices.length * BYTE_SIZE,
        GL_STREAM_DRAW_ARB);

    glEnableClientState(GL_VERTEX_ARRAY);
    glVertexPointer(VERTEX_DIMENSION, GL_FLOAT, 0, 0);
    glEnableClientState(GL11.GL_COLOR_ARRAY);
    glColorPointer(COLOR_DIMENSION, GL_FLOAT, 0, (vertices.length * BYTE_SIZE));
  }

  public void render3D()
  {
    // model view stack
    GL11.glMatrixMode(GL11.GL_MODELVIEW);

    // save current matrix
    GL11.glPushMatrix();

    // TRANSLATE
    GL11.glTranslatef(m_nX, m_nY, m_nZ);

    // ROTATE and SCALE
    if (m_rZ != 0)
      GL11.glRotatef(m_rZ, 0, 0, 1);
    if (m_rY != 0)
      GL11.glRotatef(m_rY, 0, 1, 0);
    if (m_rX != 0)
      GL11.glRotatef(m_rX, 1, 0, 0);
    if (m_sX != 1 || m_sY != 1 || m_sZ != 1)
      GL11.glScalef(m_sX, m_sY, m_sZ);

    renderModel();

    // discard current matrix
    GL11.glPopMatrix();
  }

  private void renderModel()
  {
    // Getting the ArrayBuffer to check the changes in it
    ByteBuffer newMappedBuffer = glMapBufferARB(GL_ARRAY_BUFFER_ARB,
        GL_READ_WRITE_ARB, mappedBuffer);

    // Is it the same as was maped before? If not we'll use the new buffer
    if (newMappedBuffer != mappedBuffer)
      mappedFloatBuffer = newMappedBuffer.order(ByteOrder.nativeOrder())
          .asFloatBuffer();
    // store it for next check
    mappedBuffer = newMappedBuffer;

    // We do same thing for index buffer as for vertex buffer
    newMappedBuffer = glMapBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB,
        GL_WRITE_ONLY_ARB, mappedIndicesBuffer);
    if (newMappedBuffer != mappedIndicesBuffer)
      mappedIndicesIntBuffer = newMappedBuffer.order(ByteOrder.nativeOrder())
          .asIntBuffer();
    mappedIndicesBuffer = newMappedBuffer;

    // refill new buffer with new values
    mappedFloatBuffer.rewind();
    verticesBuffer.rewind();
    colorBuffer.rewind();
    mappedFloatBuffer.put(verticesBuffer).put(colorBuffer);

    mappedIndicesIntBuffer.rewind();
    indicesBuffer.rewind();
    mappedIndicesIntBuffer.put(indicesBuffer);

    // Unmap buffers and draw triangles
    if (glUnmapBufferARB(GL_ARRAY_BUFFER_ARB)
        && glUnmapBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB))
      glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
  }

  public void morph()
  {
    // TODO Auto-generated method stub
    int i = 0;
    float[] vert = new float[12];
    for (float v : vertices)
    {
      vert[i] = v + (float) Math.random();
      System.out.print(vert[i]);
      i++;
    }
    System.out.println("");
    vertices=vert;
  }
}
