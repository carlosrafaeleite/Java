package servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.GrayFilter;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import DAO.DaoUsuario;
import beans.usuarioBeans;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class salvarUsuario extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DaoUsuario gravaUsuario = new DaoUsuario();

	public salvarUsuario() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
		if (acao != null && acao.equalsIgnoreCase("delete") && user != null) {
		gravaUsuario.deletar(user);
		RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");
			try {
				request.setAttribute("usuario", gravaUsuario.Listar());
				enviarpagina.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (acao != null && acao.equalsIgnoreCase("editar")) {
			try {
				usuarioBeans beanUser = DaoUsuario.consultar(user);
				RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");
				request.setAttribute("user", beanUser);
				enviarpagina.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (acao != null && acao.equalsIgnoreCase("listarTodos")) {
			RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");
			try {
				request.setAttribute("usuario", gravaUsuario.Listar());
				enviarpagina.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (acao != null && acao.equalsIgnoreCase("download")) {
			try {
				usuarioBeans usuario = DaoUsuario.consultar(user);
				if (usuario != null) {
					response.setHeader("Content-Disposition",
							"attachament; filename= imagem." + usuario.getContentType().split("\\/")[1]);
					byte[] imagenFoto = new Base64().decodeBase64(usuario.getFotoBase64());
					InputStream is = new ByteArrayInputStream(imagenFoto);
					int read = 0;
					byte[] bite = new byte[1024];
					OutputStream os = response.getOutputStream();
					while ((read = is.read(bite)) != -1) {
						os.write(bite, 0, read);
					}
					os.flush();
					os.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");
			try {
				request.setAttribute("usuario", gravaUsuario.Listar());
				enviarpagina.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");
			try {
				request.setAttribute("usuario", gravaUsuario.Listar());
				enviarpagina.forward(request, response);

			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String Login = request.getParameter("LoginUsuario");
			String Senha = request.getParameter("SenhaUsuario");
			String Cep = request.getParameter("cep");
			String Rua = request.getParameter("rua");
			String Bairro = request.getParameter("bairro");
			String Cidade = request.getParameter("cidade");
			String Estado = request.getParameter("estado");
			String Ibge = request.getParameter("ibge");
			String sexo = request.getParameter("sexo");
			String cargo = request.getParameter("cargo");

			usuarioBeans usuario = new usuarioBeans();
			usuario.setId(!id.isEmpty() ? Integer.parseInt(id) : 0);

			usuario.setLogin(Login);
			usuario.setSenha(Senha);
			usuario.setCep(Cep);
			usuario.setRua(Rua);
			usuario.setBairro(Bairro);
			usuario.setCidade(Cidade);
			usuario.setEstado(Estado);
			usuario.setIbge(Ibge);
			usuario.setSexo(sexo);
			usuario.setCargo(cargo);
			
			if(request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
			usuario.setAtivo(true);
			}else {
			usuario.setAtivo(false);
			}

			try {

				// pega imagem para formularios com texto

				if (ServletFileUpload.isMultipartContent(request)) {

					javax.servlet.http.Part imgFoto = request.getPart("inpFoto");

					if (imgFoto != null && imgFoto.getInputStream().available() > 0) {

						byte[] ImagensByte = converteStremByte(imgFoto.getInputStream());

						String fotoBase64 = new Base64().encodeBase64String(ImagensByte);

						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imgFoto.getContentType());

						// Inicio Miniatura de imagens

						// transformando em buffer

						byte[] imagemDecodificada = new Base64().decodeBase64(fotoBase64);
						BufferedImage bufferImagem = ImageIO.read(new ByteArrayInputStream(imagemDecodificada));

						int tipoImagem = bufferImagem.getType() == 0 ? bufferImagem.TYPE_INT_ARGB
								: bufferImagem.getType();

						// tamanho da miniatura

						BufferedImage tamanhoImagem = new BufferedImage(100, 100, tipoImagem);
						Graphics2D graf = tamanhoImagem.createGraphics();
						graf.drawImage(bufferImagem, 0, 0, 100, 100, null);
						graf.dispose();

						// Escrever imagem novamente

						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(tamanhoImagem, "png", baos);

						String miniaturaImagem = "data:image/png;base64,"
								+ DatatypeConverter.printBase64Binary(baos.toByteArray());

						usuario.setFotoBase64Miniatura(miniaturaImagem);

						// Fim Miniatura de imagens

					} else  {
						
						
						  usuario.setAtualizarImagem(false);
						//usuario.setFotoBase64(usuarioVelho.getFotoBase64());
						//usuario.setContentType(usuarioVelho.getContentType());
						//usuario.setContentType(usuarioVelho.getFotoBase64Miniatura());
						
					}

				}

				/*
				 * 
				 * para formularios somente de imagens
				 * 
				 * if (ServletFileUpload.isMultipartContent(request)) { List<FileItem> fileItems
				 * = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request); for
				 * (FileItem fileItem : fileItems) { if
				 * (fileItem.getFieldName().equals("recebe")) { String foto = new
				 * Base64().encodeBase64String(fileItem.get()); String contentType =
				 * fileItem.getContentType(); usuario.setFotoBase64(foto);
				 * usuario.setContentType(contentType);
				 * 
				 * System.out.println(foto); } } }
				 */
				// pega imagem fim

				boolean podeInserir = true;

				if (Login == null || Login.isEmpty() && Senha == null || Senha.isEmpty()) {

					RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");

					try {
						request.setAttribute("msg", "Login e Senha Obrigatorios");
						request.setAttribute("usuario", gravaUsuario.Listar());
						enviarpagina.forward(request, response);
					} catch (Exception e) {

						e.printStackTrace();
					}

				} else if (id == null || id.isEmpty() && !DaoUsuario.validar(Login)) {

					RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");

					try {
						request.setAttribute("msg", "Usuario Já Existe");
						request.setAttribute("usuario", gravaUsuario.Listar());
						enviarpagina.forward(request, response);
					} catch (Exception e) {

						e.printStackTrace();
					}

				} else if (id == null || id.isEmpty() && DaoUsuario.validar(Login)) {

					gravaUsuario.salvarUsuario(usuario);
					request.setAttribute("msg", "Usuário Salvo com Sucesso");
					RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");
					try {
						request.setAttribute("usuario", gravaUsuario.Listar());
						enviarpagina.forward(request, response);
					} catch (Exception e) {

						e.printStackTrace();
					}

				} else if (id != null && !id.isEmpty()) {
					if (!DaoUsuario.validarUpdate(Login, id)) {

						request.setAttribute("msg", "Login já Cadastrado");
					} else {
						try {
							DaoUsuario.atualizar(usuario);
							request.setAttribute("msg", "Atualizado com Sucesso");

							RequestDispatcher enviarpagina = request.getRequestDispatcher("/cadUsuario.jsp");

							request.setAttribute("usuario", gravaUsuario.Listar());
							enviarpagina.forward(request, response);

						} catch (Exception e) {

							e.printStackTrace();
						}

					}

					if (podeInserir) {

						request.setAttribute("user", usuario);

						/*
						 * try { RequestDispatcher enviarpagina =
						 * request.getRequestDispatcher("/cadUsuario.jsp");
						 * request.setAttribute("usuario", gravaUsuario.Listar());
						 * enviarpagina.forward(request, response);
						 * 
						 * 
						 * } catch (Exception e) {
						 * 
						 * e.printStackTrace(); }
						 */
					}

				}
			} catch (SQLException e1) {

				e1.printStackTrace();

			}
		}

	}

	@SuppressWarnings("unused")
	private byte[] converteStremByte(InputStream imagem) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();

	}
}