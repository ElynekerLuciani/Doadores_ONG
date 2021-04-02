package com.example.doacao_ong.ui.admin.meu_perfil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doacao_ong.R;
import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.model.Ong;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.example.doacao_ong.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class MeuPerfilFragment extends Fragment {

    private StorageReference storageReference;
    private String identificadorUsuario;

    private MeuPerfilViewModel meuPerfilViewModel;
    private static final int SELECAO_CAMERA = 10;
    private static final int SELECAO_GALERIA = 20;

    private CircleImageView imageProfile;
    private Usuario usuarioLogado;

    private Button buttonUpdate;
    private EditText inputNome;
    private EditText inputCausaONG;
    private EditText inputMissaoONG;
    private EditText inputDescricaoONG;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.meu_perfil_fragment, container, false);

        storageReference = ConfiguracaoFirebase.getFirebaseStorage();
        identificadorUsuario = UsuarioFirebase.getIdentificadorUsuario();
        usuarioLogado = UsuarioFirebase.getDadosUsuarioLogado();

        meuPerfilViewModel = new MeuPerfilViewModel();

        inputNome = root.findViewById(R.id.meu_perfil_input_nome);
        inputCausaONG = root.findViewById(R.id.meu_perfil_input_causa_ong);
        inputMissaoONG = root.findViewById(R.id.meu_perfil_input_missao_ong);
        inputDescricaoONG = root.findViewById(R.id.meu_perfil_input_descricao_ong);
        imageProfile = root.findViewById(R.id.meu_perfil_profile_image);
        buttonUpdate = root.findViewById(R.id.meu_perfil_submit_button);

        String nomeUsuario = UsuarioFirebase.getInstance().getNome();
        inputNome.setText(nomeUsuario);

        Uri url = UsuarioFirebase.getUsuarioAtual().getPhotoUrl();

        if (url != null) {
            Glide.with(getActivity()).load(url).into(imageProfile);
        } else {
            imageProfile.setImageResource(R.drawable.padrao);
        }

        requestPermissions();

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, SELECAO_CAMERA);
            }
        });

        meuPerfilViewModel.getDadosOng();

        meuPerfilViewModel.getOngLiveData().observe(getViewLifecycleOwner(), new Observer<Ong>() {
            @Override
            public void onChanged(Ong ong) {
                if (ong != null) {
                    inputMissaoONG.setText(ong.getMissao());
                    inputCausaONG.setText(ong.getCausa());
                    inputDescricaoONG.setText(ong.getDescricao());
                }
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    UsuarioFirebase.getInstance().setNome(inputNome.getText().toString());
                    UsuarioFirebase.getInstance().atualizar();

                    Ong ong = new Ong();
                    ong.setMissao(inputMissaoONG.getText().toString());
                    ong.setCausa(inputCausaONG.getText().toString());
                    ong.setDescricao(inputDescricaoONG.getText().toString());
                    ong.atualizar();

                    Toast.makeText(getActivity(), "Perfil atualizado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Campos inválidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap bitmap = null;

            try {
                switch (requestCode) {
                    case SELECAO_CAMERA:
                        assert data != null;
                        bitmap = (Bitmap) data.getExtras().get("data");
                        break;
                    case SELECAO_GALERIA:
                        assert data != null;
                        Uri locaImagemSelecionada = data.getData();
                        bitmap = MediaStore.Images.Media.getBitmap(
                                getActivity().getContentResolver(),
                                locaImagemSelecionada
                        );
                        break;
                }

                if (bitmap != null) {
                    imageProfile.setImageBitmap(bitmap);
                    //Recuperar dados de imagens do firebase
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
                    byte[] dadosBitmap = byteArrayOutputStream.toByteArray();
                    //salvar a imagem no firebase
                    final StorageReference imagemRef = storageReference
                            .child("imagens")
                            .child("perfil")
                            .child(identificadorUsuario + "perfil.jpeg");

                    UploadTask uploadTask = imagemRef.putBytes(dadosBitmap);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),
                                    "Erro ao fazer upload de imagem",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getActivity(),
                                    "Sucesso ao fazer upload de imagem",
                                    Toast.LENGTH_SHORT).show();
                            //atualizar a imagem
                            imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Uri url = task.getResult();

                                    atualizaFotoUsuario(url);
                                }
                            });


                        }
                    });

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void atualizaFotoUsuario(Uri url) {
        boolean retorno = UsuarioFirebase.atualizarFotoUsuario(url);
        if(retorno) {
            usuarioLogado.setFoto(url.toString());
            usuarioLogado.atualizar();

            Toast.makeText(getActivity(),
                    "Foto atualizada com sucesso!",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    private boolean validarCampos() {
        String nome = inputNome.getText().toString();
        String missao = inputMissaoONG.getText().toString();
        String causa = inputCausaONG.getText().toString();
        String descricao = inputDescricaoONG.getText().toString();

        return !nome.isEmpty() && !missao.isEmpty() && !causa.isEmpty() && !descricao.isEmpty();
    }
}