import java.util.*;

public class NStepXClimb
{
    static Map<String, Object> resultados = new HashMap<String, Object>();
    static int steps = 0;
    static Integer[] x;

    public static void main(String[] args)
    {
        x = new Integer[]{1, 3, 5};
        steps = 10;
        resultados = calcularNStepXClimb(steps, x);
        resultados.forEach((k, v) -> {
            Map<String, Object> mapacomparacion;
            mapacomparacion = (Map<String, Object>) v;
            System.out.println(k + mapacomparacion.get("Combinacion"));
        });
    }

    public static Map<String, Object> calcularNStepXClimb(int steps, Integer[] x)
    {
        Map<String, Object> resultados = new HashMap<String, Object>();
        long suma = 0;
        int contadorsoluciones = 0;
        ArrayList<Integer> solucion = new ArrayList<Integer>();
        Arrays.sort(x, Comparator.reverseOrder());

        for (int aux = 0; aux < x.length; aux++)
        {
            suma = x[aux];
            solucion.removeAll(solucion);
            solucion.add(x[aux]);
            //cambiar el tamaño de x
            for (int aux2 = 0; aux2 < x.length; aux2++)
            {
                Map<String, Object> res = new HashMap<String, Object>();
                suma += x[aux2];
                if (suma == steps)
                {
                    solucion.add(x[aux2]);
                    final boolean[] existe = {false};
                    resultados.forEach((k, v) -> {
                        Map<String, Object> mapacomparacion;
                        mapacomparacion = (Map<String, Object>) v;
                        String a = mapacomparacion.get("Combinacion").toString();
                        String b = solucion.toString();
                        if (a.equals(b))
                        {
                            existe[0] = true;
                        }
                    });
                    if (existe[0])
                    {
                        //                        solucion.removeAll(solucion);
                        //                        solucion.add(x[aux]);
                        //                        suma = x[aux];
                        solucion.remove(solucion.size() - 1);
                        suma -= x[aux2];
                        continue;
                    }
                    res.clear();
                    contadorsoluciones++;
                    res.put("Combinacion", solucion.clone());
                    res.put("NumeroX", solucion.stream().distinct().count());
                    resultados.put("Solucion" + contadorsoluciones, res);
                    solucion.removeAll(solucion);
                    solucion.add(x[aux]);
                    suma = x[aux];
                    aux2 = 0;
                    continue;
                }
                else if (suma < steps)
                {
                    solucion.add(x[aux2]);
                    aux2 = -1;
                }
                else
                {
                    suma -= x[aux2];
                }
            }
        }

        return resultados;
    }
}
