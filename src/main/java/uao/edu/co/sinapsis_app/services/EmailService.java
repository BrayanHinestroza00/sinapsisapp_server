package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.beans.EmailDetails;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmailDAO;
import uao.edu.co.sinapsis_app.services.interfaces.IEmailService;


@Service
public class EmailService implements IEmailService {

    @Autowired
    IEmailDAO emailDAO;

    @Override
    public boolean notificarAsignacionEtapaInicialRuta(String destinatario, String etapaAsignada) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Bienvenido a SINAPSIS UAO</h1>\n" +
                "\n" +
                "      <p>\n" +
                "        Se ha asignado tu etapa inicial dentro de la ruta de innovación y\n" +
                "        emprendimiento de SINAPSIS UAO.\n" +
                "      </p>\n" +
                "\n" +
                "      <h2 style=\"color: #9164a0; font-weight: 500\">\n" +
                "        Tu etapa en la ruta de I&E asignada es:\n" +
                "        <strong style=\"font-weight: 900\">$etapaAsignada</strong>\n" +
                "      </h2>\n" +
                "\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/qkFMxcC/ruta.jpg\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <br />\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        contenidoMensaje = contenidoMensaje.replace("$etapaAsignada", etapaAsignada.toUpperCase());

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Bienvenido a Sinapsis UAO");
        emailDetails.setTo(destinatario);

        try {
            emailDAO.sendEmail(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarAsignacionEtapaRuta(String destinatario, String etapaAsignada) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Continúa tu progreso en SINAPSIS UAO</h1>\n" +
                "\n" +
                "      <p>\n" +
                "        Se te ha asignado una nueva etapa dentro de la ruta de innovación y\n" +
                "        emprendimiento de SINAPSIS UAO.\n" +
                "      </p>\n" +
                "\n" +
                "      <h2 style=\"color: #9164a0; font-weight: 500\">\n" +
                "        Tu etapa en la ruta de I&E asignada es:\n" +
                "        <strong style=\"font-weight: 900\">$etapaAsignada</strong>\n" +
                "      </h2>\n" +
                "\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/qkFMxcC/ruta.jpg\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <br />\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        contenidoMensaje = contenidoMensaje.replace("$etapaAsignada", etapaAsignada.toUpperCase());

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Se te ha asignado una nueva etapa en la ruta de I&E");
        emailDetails.setTo(destinatario);

        try {
            emailDAO.sendEmail(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarAsignacionTarea(String destinatario, String titulo, /*String fechaLimiteEntrega,*/ String usuarioRegistra, String nombreEmprendimiento) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Tarea asignada en SINAPSIS UAO</h1>\n" +
                "\n" +
                "      <p>\n" +
                "        Se te ha asignado una tarea dentro de la ruta de innovación y\n" +
                "        emprendimiento de SINAPSIS UAO.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <b>Titulo:</b> $titulo <br />\n" +
//                "        <b>Fecha limite de entrega:</b> $fechaLimiteEntrega <br />\n" +
                "        <b>Usuario que registra:</b> $usuarioRegistra <br />\n" +
                "        <b>Proyecto emprendimiento:</b> $nombreEmprendimiento <br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        contenidoMensaje = contenidoMensaje.replace("$titulo",titulo)
//                .replace("$fechaLimiteEntrega",fechaLimiteEntrega)
                .replace("$usuarioRegistra",usuarioRegistra)
                .replace("$nombreEmprendimiento",nombreEmprendimiento);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Se te ha asignado una nueva tarea");
        emailDetails.setTo(destinatario);

        try {
            emailDAO.sendEmail(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarCalificacionTarea(String destinatario, String titulo, String fechaEntrega, String usuarioCalifica, String calificacion, String nombreEmprendimiento) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Tarea calificada en SINAPSIS UAO</h1>\n" +
                "\n" +
                "      <p>\n" +
                "        Se ha calificado tu tarea de la ruta de innovación y emprendimiento de\n" +
                "        SINAPSIS UAO.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <b>Titulo:</b> $titulo <br />\n" +
                "        <b>Fecha entrega:</b> $fechaEntrega <br />\n" +
                "        <b>Calificación:</b> $calificacionHtml <br />\n" +
                "        <b>Usuario que califica:</b> $usuarioCalifica <br />\n" +
                "        <b>Proyecto emprendimiento:</b> $nombreEmprendimiento <br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        String calificacionHtml = calificacion.equalsIgnoreCase("APROBADO")
                ? "<span style=\"color: #8fbc8f\">APROBADO</span>" : "<span style=\"color: #e9967a\">REPROBADO</span>";

        contenidoMensaje = contenidoMensaje.replace("$calificacionHtml", calificacionHtml)
                .replace("$titulo", titulo)
                .replace("$fechaEntrega",fechaEntrega )
                .replace("$usuarioCalifica", usuarioCalifica)
                .replace("$nombreEmprendimiento", nombreEmprendimiento);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Se ha calificado tu tarea");
        emailDetails.setTo(destinatario);

        try {
            emailDAO.sendEmail(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarEntregaTarea(String[] destinatarios, String titulo, /*String fechaLimiteEntrega,*/ String fechaEntrega, String emprendedorEntrega, String nombreEmprendimiento) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Tarea entregada en SINAPSIS UAO</h1>\n" +
                "\n" +
                "      <p>\n" +
                "        El emprendedor ha realizado la entrega de una tarea relacionada a un\n" +
                "        proyecto de emprendimiento asociado.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <b>Titulo:</b> $titulo <br />\n" +
//                "        <b>Fecha limite de entrega:</b> $fechaLimiteEntrega <br />\n" +
                "        <b>Fecha entrega:</b> $fechaEntrega <br />\n" +
                "        <b>Emprendedor:</b> $emprendedorEntrega <br />\n" +
                "        <b>Proyecto emprendimiento:</b> $nombreEmprendimiento <br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";


        contenidoMensaje = contenidoMensaje.replace("$titulo", titulo)
//                .replace("$fechaLimiteEntrega", fechaLimiteEntrega)
                .replace("$fechaEntrega", fechaEntrega)
                .replace("$emprendedorEntrega", emprendedorEntrega)
                .replace("$nombreEmprendimiento", nombreEmprendimiento);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Se ha realizado la entrega de una tarea");
        emailDetails.setMultipleTo(destinatarios);

        try {
            emailDAO.sendEmailMultiple(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarProgramacionConsultoria(String[] destinatarios, String asuntoConsultoria, String fechaConsultoria, String horaInicioProgramada,
                                                    String horaFinProgramada, String nombreEmprendedor, String nombreCrea, String nombreProyecto ) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Consultoría programada en SINAPSIS UAO</h1>\n" +
                "\n" +
                "      <p>Se te ha programado una consultoría en SINAPSIS UAO.</p>\n" +
                "      <p>\n" +
                "        <b>Asunto consultoría:</b> $asuntoConsultoria <br />\n" +
                "        <b>Fecha de consultoría:</b> $fechaConsultoria <br />\n" +
                "        <b>Hora de inicio programada:</b> $horaInicioProgramada <br />\n" +
                "        <b>Hora de finalización programada:</b> $horaFinProgramada <br />\n" +
                "        <b>Emprendedor:</b> $nombreEmprendedor <br />\n" +
                "        <b>Usuario programa:</b> $nombreCrea <br />\n" +
                "        <b>Proyecto emprendimiento:</b> $nombreProyecto <br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        contenidoMensaje = contenidoMensaje.replace("$asuntoConsultoria", asuntoConsultoria)
                .replace("$fechaConsultoria", fechaConsultoria)
                .replace("$horaInicioProgramada",horaInicioProgramada)
                .replace("$horaFinProgramada", horaFinProgramada)
                .replace("$nombreEmprendedor", nombreEmprendedor)
                .replace("$nombreCrea", nombreCrea)
                .replace("$nombreProyecto", nombreProyecto);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Se ha programado una consultoría");
        emailDetails.setMultipleTo(destinatarios);

        try {
            emailDAO.sendEmailMultiple(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarAsignacionNuevoEmprendedor(String destinatario, String nombreEmprendedor, String correoEmprendedor, String nombreProyecto, String etapaRuta) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Emprendedor asignado en SINAPSIS UAO</h1>\n" +
                "\n" +
                "      <p>\n" +
                "        Se te ha asociado un nuevo emprendedor dentro de la ruta de innovación y\n" +
                "        emprendimiento de SINAPSIS UAO.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <b>Emprendedor:</b> $nombreEmprendedor <br />\n" +
                "        <b>Correo de contacto:</b>\n" +
                "        $correoEmprendedor <br />\n" +
                "        <b>Proyecto emprendimiento:</b> $nombreProyecto <br />\n" +
                "        <b>Etapa en la ruta de I&E:</b> $etapaRuta <br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        contenidoMensaje = contenidoMensaje.replace("$nombreEmprendedor", nombreEmprendedor)
                        .replace("$correoEmprendedor", correoEmprendedor)
                        .replace("$nombreProyecto", nombreProyecto)
                        .replace("$etapaRuta", etapaRuta);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Se te ha asignado un nuevo emprendedor");
        emailDetails.setTo(destinatario);

        try {
            emailDAO.sendEmail(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarAsignacionNuevoMentor(String destinatario, String nombreMentor, String correoMentor, String cargoMentor, String nombreProyecto, String etapaRuta) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Te han asignado un mentor en SINAPSIS UAO</h1>\n" +
                "\n" +
                "      <p>\n" +
                "        Se te ha asociado un nuevo mentor dentro de la ruta de innovación y\n" +
                "        emprendimiento de SINAPSIS UAO.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <b>Mentor:</b> $nombreMentor <br />\n" +
                "        <b>Correo de contacto:</b>\n" +
                "        $correoMentor <br />\n" +
                "        <b>Cargo:</b> $cargoMentor <br />\n" +
                "        <b>Proyecto emprendimiento:</b> $nombreProyecto <br />\n" +
                "        <b>Etapa en la ruta de I&E:</b> $etapaRuta <br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        contenidoMensaje = contenidoMensaje.replace("$nombreMentor", nombreMentor)
                .replace("$correoMentor", correoMentor)
                .replace("$cargoMentor", cargoMentor)
                .replace("$nombreProyecto", nombreProyecto)
                .replace("$etapaRuta", etapaRuta);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Se te ha asignado un nuevo mentor");
        emailDetails.setTo(destinatario);

        try {
            emailDAO.sendEmail(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarCulminacionActividadesMentor(EmailDetails emailDetails) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">\n" +
                "        Emprendedor pendiente de aprobación en SINAPSIS UAO\n" +
                "      </h1>\n" +
                "\n" +
                "\n" +
                "      <p>\n" +
                "        Un emprendedor asociado ha completado la entrega de todas las\n" +
                "        actividades para la etapa en la ruta de innovación y emprendimiento de\n" +
                "        SINAPSIS UAO.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <b>Emprendedor:</b> Diana Johanna Restrepo Ortega<br />\n" +
                "        <b>Correo de contacto:</b>\n" +
                "        djrestrepo@uao.edu.co<br />\n" +
                "        <b>Proyecto emprendimiento:</b> Emprendimiento novedoso<br />\n" +
                "        <b>Etapa en la ruta de I&E:</b> SOÑAR<br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <p style=\"color: #9164a0; font-weight: 700\">\n" +
                "        Por favor, verifique y evalué todos los entregables y posteriormente\n" +
                "        finalice el asesoramiento.\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Emprendedor pendiente de revisión de actividades");
        emailDetails.setTo("brayan.hinestroza@uao.edu.co");

        try {
            emailDAO.sendEmail(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarCulminacionRutaEmprendedor(String[]  destinatarios, String nombreEmprendedor, String correoEmprendedor, String nombreProyecto) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">\n" +
                "        Emprendedor ha culminado todas las etapas en SINAPSIS UAO\n" +
                "      </h1>\n" +
                "\n" +
                "      <p>\n" +
                "        Un emprendedor ha completado toda la ruta de innovación y emprendimiento\n" +
                "        de SINAPSIS UAO.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <b>Emprendedor:</b> $nombreEmprendedor <br />\n" +
                "        <b>Correo de contacto:</b>\n" +
                "        $correoEmprendedor <br />\n" +
                "        <b>Proyecto emprendimiento:</b> $nombreProyecto <br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        contenidoMensaje = contenidoMensaje.replace("$nombreEmprendedor", nombreEmprendedor)
                .replace("$correoEmprendedor", correoEmprendedor)
                .replace("$nombreProyecto", nombreProyecto);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Un emprendedor ha culminado la ruta de I&E");
        emailDetails.setMultipleTo(destinatarios);

        try {
            emailDAO.sendEmailMultiple(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean notificarSolicitudPrimeraAtencion(String[] destinatarios, String nombreEmprendedor, String documentoEmprendedor, String vinculoConU, String correoInstitucional, String correoPersonal, String nombreEmprendimiento) {
        String contenidoMensaje = "<div\n" +
                "      style=\"\n" +
                "        text-align: center;\n" +
                "        background-color: #fbf9fb;\n" +
                "        font-family: 'Montserrat';\n" +
                "        max-width: 70vw;\n" +
                "        margin: 0 auto;\n" +
                "      \"\n" +
                "    >\n" +
                "      <figure>\n" +
                "        <img src=\"https://i.ibb.co/8Ms0c0Q/logo-sinapsis.png\" />\n" +
                "      </figure>\n" +
                "\n" +
                "      <h1 style=\"color: #9164a0\">Primera atención pendiente de revisión</h1>\n" +
                "\n" +
                "      <p>\n" +
                "        Un emprendedor ha registrado un nuevo proyecto de emprendimiento, el\n" +
                "        cual requiere el análisis de la primera atención.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <b>Emprendedor:</b> $nombreEmprendedor <br />\n" +
                "        <b>Documento Emprendedor:</b> $documentoEmprendedor <br />\n" +
                "        <b>Vinculo con la Universidad:</b> $vinculoConU <br />\n" +
                "        <b>Correo institucional:</b> $correoInstitucional <br />\n" +
                "        <b>Correo Personal:</b> $correoPersonal <br />\n" +
                "        <b>Proyecto emprendimiento:</b> $nombreEmprendimiento <br />\n" +
                "        <br />\n" +
                "      </p>\n" +
                "\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <p>\n" +
                "                Si tiene algún inconveniente o duda, no dude en comunicarse con\n" +
                "                nosotros&nbsp;<br /><a\n" +
                "                  href=\"mailto:sinapsis@uao.edu.co\"\n" +
                "                  target=\"_blank\"\n" +
                "                  >sinapsis@uao.edu.co</a\n" +
                "                >\n" +
                "              </p>\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\"></table>\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              <strong>PBX:</strong>602 318 8000 ext. 11046 – 11046\n" +
                "              <br />\n" +
                "              <strong>Línea gratuita:</strong> 01 8000 91 34 35\n" +
                "              <br />\n" +
                "              Calle 25 # 115 - 85 <br />\n" +
                "              Vía Cali - Jamundí\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td height=\"50\" valign=\"top\" align=\"center\">\n" +
                "              <hr />\n" +
                "              <span style=\"font-size: xx-small\"\n" +
                "                >Personería jurídica, Res. No. 0618, de la Gobernación del Valle\n" +
                "                del Cauca, del 20 de febrero de 1970. Universidad Autónoma de\n" +
                "                Occidente, Res. No. 2766, del Ministerio de Educación Nacional,\n" +
                "                del 13 de noviembre de 2003.&nbsp;Acreditación Institucional de\n" +
                "                Alta Calidad, Res. No. 16740, del 24 de agosto de 2017, con\n" +
                "                vigencia hasta el 2021. Vigilada MinEducación. La información\n" +
                "                enviada en este correo (i) no constituye la posición oficial de\n" +
                "                la Universidad Autónoma de Occidente, incluido cualquier archivo\n" +
                "                adjunto, (ii) está dirigida a la persona a la cual es remitida y\n" +
                "                (iii) puede contener información confidencial o privilegiada.\n" +
                "                Toda revisión, retransmisión, publicación, reenvío masivo u otra\n" +
                "                acción realizada sobre ésta información por personas diferentes\n" +
                "                al destinatario está prohibida, y no habrá surgimiento de\n" +
                "                responsabilidad alguna por dicha razón por parte de La\n" +
                "                Universidad Autónoma de Occidente. Si usted recibe por error\n" +
                "                este correo, por favor notifique al remitente y borre toda la\n" +
                "                información recibida de todos los dispositivos en los cuales\n" +
                "                ésta repose.</span\n" +
                "              >\n" +
                "              <hr />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>";

        contenidoMensaje = contenidoMensaje.replace("$nombreEmprendedor", nombreEmprendedor)
                .replace("$documentoEmprendedor", documentoEmprendedor)
                .replace("$vinculoConU",vinculoConU)
                .replace("$correoInstitucional",correoInstitucional)
                .replace("$correoPersonal",correoPersonal)
                .replace("$nombreEmprendimiento",nombreEmprendimiento);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setCuerpoMensaje(contenidoMensaje);
        emailDetails.setAsunto("Solicitud de Primera Atención");
        emailDetails.setMultipleTo(destinatarios);

        try {
            emailDAO.sendEmailMultiple(emailDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
